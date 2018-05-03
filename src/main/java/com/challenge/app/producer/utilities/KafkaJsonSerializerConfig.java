/**
 * Copyright 2015 Confluent Inc.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package com.challenge.app.producer.utilities;

import org.apache.kafka.common.config.AbstractConfig;
import org.apache.kafka.common.config.ConfigDef;

import java.util.Map;

class KafkaJsonSerializerConfig extends AbstractConfig {

    static final String JSON_INDENT_OUTPUT = "json.indent.output";
    static final boolean JSON_INDENT_OUTPUT_DEFAULT = true;
    static final String JSON_INDENT_OUTPUT_DOC =
            "Whether JSON output should be indented (\"pretty-printed\")";

    private static ConfigDef config;

    static {
        config = new ConfigDef()
                .define(JSON_INDENT_OUTPUT, ConfigDef.Type.BOOLEAN, JSON_INDENT_OUTPUT_DEFAULT,
                        ConfigDef.Importance.LOW, JSON_INDENT_OUTPUT_DOC);
    }

    KafkaJsonSerializerConfig(Map<?, ?> props) {
        super(config, props);
    }

}