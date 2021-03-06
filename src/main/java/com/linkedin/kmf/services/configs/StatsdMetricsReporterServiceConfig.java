/**
 * Copyright 2016 LinkedIn Corp. Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *
 * In order to enable the StatsD metrics export, add the following section to kafka-monitor.properties file
 *
 ==========================================================================================
  "statsd-service": {
       "class.name": "com.linkedin.kmf.services.StatsdMetricsReporterService",
       "report.statsd.host": "localhost",
       "report.statsd.port": "8125",
       "report.statsd.prefix": "kafka-monitor",
       "report.interval.sec": 1,
       "report.metrics.list": [
       "kmf.services:type=produce-service,name=*:produce-availability-avg",
       "kmf.services:type=consume-service,name=*:consume-availability-avg",
       "kmf.services:type=produce-service,name=*:records-produced-total",
       "kmf.services:type=consume-service,name=*:records-consumed-total",
       "kmf.services:type=consume-service,name=*:records-lost-total",
       "kmf.services:type=consume-service,name=*:records-duplicated-total",
       "kmf.services:type=consume-service,name=*:records-delay-ms-avg",
       "kmf.services:type=produce-service,name=*:records-produced-rate",
       "kmf.services:type=produce-service,name=*:produce-error-rate",
       "kmf.services:type=consume-service,name=*:consume-error-rate"
     ]
   }
 ==========================================================================================
 */
package com.linkedin.kmf.services.configs;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Arrays;
import java.util.Map;

public class StatsdMetricsReporterServiceConfig extends AbstractConfig {
  private static final ConfigDef CONFIG;

  public static final String REPORT_METRICS_CONFIG = CommonServiceConfig.REPORT_METRICS_CONFIG;
  public static final String REPORT_METRICS_DOC = CommonServiceConfig.REPORT_METRICS_DOC;

  public static final String REPORT_INTERVAL_SEC_CONFIG = CommonServiceConfig.REPORT_INTERVAL_SEC_CONFIG;
  public static final String REPORT_INTERVAL_SEC_DOC = CommonServiceConfig.REPORT_INTERVAL_SEC_DOC;

  public static final String REPORT_STATSD_HOST = "report.statsd.host";
  public static final String REPORT_STATSD_HOST_DOC = "The host of statsd server which StatsdMetricsReporterService will report the metrics values.";

  public static final String REPORT_STATSD_PORT = "report.statsd.port";
  public static final String REPORT_STATSD_PORT_DOC = "The port of statsd server which StatsdMetricsReporterService will report the metrics values.";

  public static final String REPORT_STATSD_PREFIX = "report.statsd.prefix";
  public static final String REPORT_STATSD_PREFIX_DOC = "The prefix of statsd metric name that will be generated with metric name to report to graphite server.";

  static {
    CONFIG = new ConfigDef().define(REPORT_METRICS_CONFIG,
                                    ConfigDef.Type.LIST,
                                    Arrays.asList("kmf.services:*:*"),
                                    ConfigDef.Importance.MEDIUM,
                                    REPORT_METRICS_DOC)
                            .define(REPORT_INTERVAL_SEC_CONFIG,
                                    ConfigDef.Type.INT,
                                    1,
                                    ConfigDef.Importance.LOW,
                                    REPORT_INTERVAL_SEC_DOC)
                            .define(REPORT_STATSD_HOST,
                                    ConfigDef.Type.STRING,
                                    "localhost",
                                    ConfigDef.Importance.MEDIUM,
                                    REPORT_STATSD_HOST_DOC)
                            .define(REPORT_STATSD_PORT,
                                    ConfigDef.Type.INT,
                                    8125,
                                    ConfigDef.Importance.MEDIUM,
                                    REPORT_STATSD_PORT_DOC)
                            .define(REPORT_STATSD_PREFIX,
                                    ConfigDef.Type.STRING,
                                    "",
                                    ConfigDef.Importance.LOW,
                                    REPORT_STATSD_PREFIX_DOC);

  }

  public StatsdMetricsReporterServiceConfig(Map<?, ?> props) {
    super(CONFIG, props);
  }
}
