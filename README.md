# springboot整合nacos做配置中心的一次问题排查

这里先把问题陈述一下：**springboot整合nacos做配置中心，使用@NacosConfigurationProperties注解注入bean属性，当nacos前台客户端没有配置这个bean的属性，项目代码本地配置文件配置了，此时bean的属性并没有注入进来**

我们都知道`nacos`既可以做注册中心，又可以做配置中心，这里我们主要讲`nacos`做配置中心，从而实现**配置动态刷新**。

### 1.springboot整合nacos做配置中心

springboot整合nacos很简单，只需要几步：

**引入依赖**

```xml
      <dependency>
        <groupId>com.alibaba.boot</groupId>
        <artifactId>nacos-config-spring-boot-starter</artifactId>
        <version>0.2.10</version>
      </dependency>
```

**添加配置**：项目本地配置文件application.yml

```yml
nacos:
  config:
    server-addr: 10.10.0.14:8848
    namespace: 6de43a50-61da-426a-9b2c-df474034c13d
    type: properties
    data-id: example.properties
    bootstrap:
      enable: true
 user1:
  name: shepherd-local
  age: 18
  id: 127
```

**使用案例**

```java
/**
 * 注意   注意    注意
 * 使用@ConfigurationProperties注解如果不走配置中心，或者走配置中心但没有配置当前配置属性信息，这时候会加载本地配置问题
 * 使用@ConfigurationProperties不会动态更新
 * 使用@NacosConfigurationProperties注解如果不走配置中心，或者走配置中心但没有配置当前配置属性信息，这时候即时本地文件配置了也不会加载注入属性
 */
@Data
@Component
@NacosConfigurationProperties(prefix = "user1", autoRefreshed = true, dataId = "example.properties")
//@ConfigurationProperties(prefix = "user1")
public class User {
    private String name;
    private Integer age;
    private Long id;
}
```

这时候我们启动项目，获取user bean对象，你会发现，假如我们没有在nacos前台客户端的添加相应的属性配置，但是在项目代码本地配置了，如上所示，却发现**属性没有注入进来！！！这时候就很疑惑了，按道理，如果nacos配置中心没有配置，是不是应该读取本地配置文件的配置呢？**我想大部分的人都是这么认为的，当然也有可能是我们自己使用不当，为了消除疑惑，接下来只能debug源码了

### 2.源码解析

我们都知道，按照spring boot的自动装配原理，要开启什么功能，只需要引入依赖，就会有一个`xxxAutoConfiguration`自动配置类，nacos也不例外，在导入的nacos依赖jar包里面可以找到自动配置的**spring.factories**文件：

```properties
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
  com.alibaba.boot.nacos.config.autoconfigure.NacosConfigAutoConfiguration
#org.springframework.context.ApplicationContextInitializer=\
#  com.alibaba.boot.nacos.config.autoconfigure.NacosConfigApplicationContextInitializer
org.springframework.boot.env.EnvironmentPostProcessor=\
  com.alibaba.boot.nacos.config.autoconfigure.NacosConfigEnvironmentProcessor
org.springframework.context.ApplicationListener=\
  com.alibaba.boot.nacos.config.logging.NacosLoggingListener
```

这里发现我导入的0.2.10版本的`NacosConfigApplicationContextInitializer`被注释掉了， ApplicationContextInitializer 是一个回调接口，用于 Spring ConfigurableApplicationContext 容器执行 `#refresh()` 方法进行初始化之前，提前走一些自定义的初始化逻辑。这里注释掉说明不会走自动装配，说明在启动时候别的地方会加载这个`NacosConfigApplicationContextInitializer`初始化类：

从`SpringApplication`类`run()`方法开始：

```java
	public static ConfigurableApplicationContext run(Class<?> primarySource, String... args) {
		return run(new Class<?>[] { primarySource }, args);
	}

	public static ConfigurableApplicationContext run(Class<?>[] primarySources, String[] args) {
		return new SpringApplication(primarySources).run(args);
	}
```

到`SpringApplication`的构造方法：

```java
	public SpringApplication(ResourceLoader resourceLoader, Class<?>... primarySources) {
		this.resourceLoader = resourceLoader;
		Assert.notNull(primarySources, "PrimarySources must not be null");
		this.primarySources = new LinkedHashSet<>(Arrays.asList(primarySources));
		this.webApplicationType = WebApplicationType.deduceFromClasspath();
		setInitializers((Collection) getSpringFactoriesInstances(ApplicationContextInitializer.class));
		setListeners((Collection) getSpringFactoriesInstances(ApplicationListener.class));
		this.mainApplicationClass = deduceMainApplicationClass();
	}
```

`setInitializers()`方法就是加载各种`ApplicationContextInitializer`初始化类的，但是这里是从**spring.factories**中查找、自动装配，而我们根据前面分享已经知道被注释掉了，自然这里是加载不到`NacosConfigApplicationContextInitializer`类，那说明是在后面执行的`run()`方法加载进来的：

```java
public ConfigurableApplicationContext run(String... args) {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ConfigurableApplicationContext context = null;
		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
		configureHeadlessProperty();
		SpringApplicationRunListeners listeners = getRunListeners(args);
		listeners.starting();
		try {
			ApplicationArguments applicationArguments = new DefaultApplicationArguments(args);
			ConfigurableEnvironment environment = prepareEnvironment(listeners, applicationArguments);
			configureIgnoreBeanInfo(environment);
			Banner printedBanner = printBanner(environment);
			context = createApplicationContext();
			exceptionReporters = getSpringFactoriesInstances(SpringBootExceptionReporter.class,
					new Class[] { ConfigurableApplicationContext.class }, context);
			prepareContext(context, environment, listeners, applicationArguments, printedBanner);
			refreshContext(context);
			afterRefresh(context, applicationArguments);
			stopWatch.stop();
			if (this.logStartupInfo) {
				new StartupInfoLogger(this.mainApplicationClass).logStarted(getApplicationLog(), stopWatch);
			}
			listeners.started(context);
			callRunners(context, applicationArguments);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, exceptionReporters, listeners);
			throw new IllegalStateException(ex);
		}

		try {
			listeners.running(context);
		}
		catch (Throwable ex) {
			handleRunFailure(context, ex, exceptionReporters, null);
			throw new IllegalStateException(ex);
		}
		return context;
	}

```

跟源码你会发现：在执行`prepareEnvironment()`方法会调用`NacosConfigEnvironmentProcessor`的`postProcessEnvironment()`方法：

```java
	@Override
	public void postProcessEnvironment(ConfigurableEnvironment environment,
			SpringApplication application) {
		application.addInitializers(new NacosConfigApplicationContextInitializer(this));
		nacosConfigProperties = NacosConfigPropertiesUtils
				.buildNacosConfigProperties(environment);
		if (enable()) {
			System.out.println(
					"[Nacos Config Boot] : The preload log configuration is enabled");
			loadConfig(environment);
		}
	}
```

这里就很清楚看到，把`NacosConfigApplicationContextInitializer`添加到了初始化类集合中。接下来执行`SpringApplication`的

`prepareContext`方法，其调用了`applyInitializers()`方法：

```java
	protected void applyInitializers(ConfigurableApplicationContext context) {		for (ApplicationContextInitializer initializer : getInitializers()) {			Class<?> requiredType = GenericTypeResolver.resolveTypeArgument(initializer.getClass(),					ApplicationContextInitializer.class);			Assert.isInstanceOf(requiredType, context, "Unable to call initializer.");			initializer.initialize(context);		}	}
```

这里执行各个`ApplicationContextInitializer`初始化类的回调方法`initialize()`，显然`NacosConfigApplicationContextInitializer`的初始化方法也是在这里执行了

```java
	@Override	public void initialize(ConfigurableApplicationContext context) {		singleton.setApplicationContext(context);		environment = context.getEnvironment();		nacosConfigProperties = NacosConfigPropertiesUtils				.buildNacosConfigProperties(environment);		final NacosConfigLoader configLoader = new NacosConfigLoader(				nacosConfigProperties, environment, builder);		if (!enable()) {			logger.info("[Nacos Config Boot] : The preload configuration is not enabled");		}		else {			// If it opens the log level loading directly will cache			// DeferNacosPropertySource release			if (processor.enable()) {				processor.publishDeferService(context);				configLoader						.addListenerIfAutoRefreshed(processor.getDeferPropertySources());			}			else {				configLoader.loadConfig();				configLoader.addListenerIfAutoRefreshed();			}		}		final ConfigurableListableBeanFactory factory = context.getBeanFactory();		if (!factory				.containsSingleton(NacosBeanUtils.GLOBAL_NACOS_PROPERTIES_BEAN_NAME)) {			factory.registerSingleton(NacosBeanUtils.GLOBAL_NACOS_PROPERTIES_BEAN_NAME,					configLoader.buildGlobalNacosProperties());		}	}
```

这样，nacos的上下文环境配置就初始化好了

自动配置类

```java
@ConditionalOnProperty(name = NacosConfigConstants.ENABLED, matchIfMissing = true)@ConditionalOnMissingBean(name = CONFIG_GLOBAL_NACOS_PROPERTIES_BEAN_NAME)@EnableConfigurationProperties(value = NacosConfigProperties.class)@ConditionalOnClass(name = "org.springframework.boot.context.properties.bind.Binder")@Import(value = { NacosConfigBootBeanDefinitionRegistrar.class })@EnableNacosConfigpublic class NacosConfigAutoConfiguration {}
```

这里`@Condition`相关注解就是典型的条件装配，没什么好说的，`@EnableConfigurationProperties`开启`NacosConfigProperties`类的属性注入，最后是用`@Import`导入`NacosConfigBootBeanDefinitionRegistrar`类，这是我们需要重点关注的地方

```java
@Configurationpublic class NacosConfigBootBeanDefinitionRegistrar		implements ImportBeanDefinitionRegistrar, BeanFactoryAware {	@Override	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {		DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) beanFactory;		BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder				.rootBeanDefinition(NacosBootConfigurationPropertiesBinder.class);		defaultListableBeanFactory.registerBeanDefinition(				NacosBootConfigurationPropertiesBinder.BEAN_NAME,				beanDefinitionBuilder.getBeanDefinition());	}	@Override	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata,			BeanDefinitionRegistry registry) {	}}
```

这里将`NacosBootConfigurationPropertiesBinder`类以beanName为`nacosConfigurationPropertiesBinder`注册到deanDefinition注册中心，方便后续获取这个bean。

接下来跟源码就到`NacosConfigurationPropertiesBindingPostProcessor`，看类名称就知道这个类是对使用了`@NacosConfigurationProperties`注解的类进行属性绑定的

```java
public class NacosConfigurationPropertiesBindingPostProcessor		implements BeanPostProcessor, ApplicationContextAware {	/**	 * The name of {@link NacosConfigurationPropertiesBindingPostProcessor} Bean	 */	public static final String BEAN_NAME = "nacosConfigurationPropertiesBindingPostProcessor";	private Properties globalNacosProperties;	private NacosServiceFactory nacosServiceFactory;	private Environment environment;	private ApplicationEventPublisher applicationEventPublisher;	private ConfigurableApplicationContext applicationContext;	@Override	public Object postProcessBeforeInitialization(Object bean, String beanName)			throws BeansException {		NacosConfigurationProperties nacosConfigurationProperties = findAnnotation(				bean.getClass(), NacosConfigurationProperties.class);		if (nacosConfigurationProperties != null) {			bind(bean, beanName, nacosConfigurationProperties);		}		return bean;	}	private void bind(Object bean, String beanName,			NacosConfigurationProperties nacosConfigurationProperties) {		NacosConfigurationPropertiesBinder binder;		try {			binder = applicationContext.getBean(					NacosConfigurationPropertiesBinder.BEAN_NAME,					NacosConfigurationPropertiesBinder.class);			if (binder == null) {				binder = new NacosConfigurationPropertiesBinder(applicationContext);			}		}		catch (Exception e) {			binder = new NacosConfigurationPropertiesBinder(applicationContext);		}		binder.bind(bean, beanName, nacosConfigurationProperties);	}	@Override	public Object postProcessAfterInitialization(Object bean, String beanName)			throws BeansException {		return bean;	}	@Override	public void setApplicationContext(ApplicationContext applicationContext)			throws BeansException {		this.applicationContext = (ConfigurableApplicationContext) applicationContext;	}}
```

从上面源码可以看出，实现了`BeanPostProcessor`, 并且重写了前置处理方法`postProcessBeforeInitialization`，这里判断当前bean有没有使用`NacosConfigurationProperties`注解， 有的话接着就做属性注入绑定操作。

属性注入绑定最终执行到`NacosBootConfigurationPropertiesBinder`的`dobinder()`方法：**这是消除疑惑核心方法**

```java
	@Override	protected void doBind(Object bean, String beanName, String dataId, String groupId,			String configType, NacosConfigurationProperties properties, String content,			ConfigService configService) {		synchronized (this) {			String name = "nacos-bootstrap-" + beanName;      // 从nacos配置中心加载属性源			NacosPropertySource propertySource = new NacosPropertySource(name, dataId, groupId, content, configType);			// 将属性源放到environment环境中      environment.getPropertySources().addLast(propertySource);      // 绑定环境			Binder binder = Binder.get(environment);			ResolvableType type = getBeanType(bean, beanName);			Bindable<?> target = Bindable.of(type).withExistingValue(bean);      // 这里绑定了属性			binder.bind(properties.prefix(), target);			publishBoundEvent(bean, beanName, dataId, groupId, properties, content, configService);			publishMetadataEvent(bean, beanName, dataId, groupId, properties);			environment.getPropertySources().remove(name);		}	}
```

**这里我们发现`environment`是在当前类new出来的，然后从nacos配置中心获取属性源加入到当前`environment`中，并没有从spring上下文中获取`environment`，所以代码本地的配置文件这里是不会被操作的，自然本地配置的属性也不能被注入进来**

```java
	private StandardEnvironment environment = new StandardEnvironment();
```

至于为什么这么实现不得而知。。。

