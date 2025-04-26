/**
 * 规范定义函数
 *
 * @param options
 * @returns {{name: string, description: string, attributes: [{dataType: string, name: string},{dataType: string, name: string, properties: [{dataType: string, name: string},{dataType: string, name: string}]},{dataType: string, name: string, items: {__ref: string, dataType: string}}], configClassName: string}}
 */
(options) => {
    return {
        "name": "descriptor",
        "description": "这是一个规范，对应的配置类型是SomeConfig",
        "configClassName": "io.github.cloudstars.lowcode.commons.descriptor.SomeConfig",
        "attributes": [
            {
                "name": "x",
                "dataType": "STRING",
            },
            {
                "name": "properties",
                "dataType": "OBJECT",
                "properties": [{
                    "name": "aaa",
                    "dataType": "STRING",
                }, {
                    "name": "bbb",
                    "dataType": "STRING",
                }]
            },
            {
                "name": "items",
                "dataType": "ARRAY",
                "items": {
                    "dataType": "REFERENCE",
                    "descriptor": "descriptorRef",
                }
            }
        ]
    };
};