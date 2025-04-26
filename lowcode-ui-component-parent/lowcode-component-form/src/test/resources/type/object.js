(options) => {
    return {
        "name": "data-type-object",
        "description": "这是对象数据格式规范",
        "extends": "data-type-common",
        "attributes": [
            {
                "name": "properties",
                "required": false,
                "description": "对象下可以有0到多个属性，每个属性符合data-type-commons规范",
                "ref-type": "data-type-common"
            }
        ]
    };
}