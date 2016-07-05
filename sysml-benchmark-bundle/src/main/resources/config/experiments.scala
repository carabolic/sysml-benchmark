package config

import org.springframework.context.annotation._
import org.springframework.context.{ApplicationContext, ApplicationContextAware}

/** Experiments definitions for the 'sysml-benchmark' bundle. */
@Configuration
@ComponentScan( // Scan for annotated Peel components in the 'eu.stratosphere.benchmarks.systemml' package
  value = Array("eu.stratosphere.benchmarks.systemml"),
  useDefaultFilters = false,
  includeFilters = Array[ComponentScan.Filter](
    new ComponentScan.Filter(value = Array(classOf[org.springframework.stereotype.Service])),
    new ComponentScan.Filter(value = Array(classOf[org.springframework.stereotype.Component]))
  )
)
@ImportResource(value = Array(
  "classpath:peel-core.xml",
  "classpath:peel-extensions.xml"
))
@Import(value = Array(
  classOf[org.peelframework.extensions], // custom system beans
  classOf[config.fixtures.systems],      // custom system beans
  classOf[config.fixtures.wordcount],     // wordcount experiment beans
  classOf[config.fixtures.linregDS]
))
class experiments extends ApplicationContextAware {

  /* The enclosing application context. */
  var ctx: ApplicationContext = null

  def setApplicationContext(ctx: ApplicationContext): Unit = {
    this.ctx = ctx
  }
}