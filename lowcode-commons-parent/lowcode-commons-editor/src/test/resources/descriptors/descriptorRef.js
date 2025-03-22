/**
 * 规范定义函数
 *
 * @param options
 * @returns {{name: string, description: string, attributes: [{dataType: string, name: string},{dataType: string, name: string}], configClassName: string}}
 */
(options) => {
    return {
        "name": "descriptorRef",
        "description": "这是规范Ref",
        "configClassName": "io.github.cloudstars.lowcode.commons.editor.SomeAttribute",
        "attributes": [
            {
                "name": "attr1",
                "dataType": "STRING"
            },
            {
                "name": "attr2",
                "dataType": "STRING"
            }
        ]
    };
};