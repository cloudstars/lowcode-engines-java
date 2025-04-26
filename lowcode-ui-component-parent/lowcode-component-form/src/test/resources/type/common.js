(options) => {
    return {
        "name": "data-type-common",
        "description": "这是文本数据格式规范",
        "attributes": [
            {
                "name": "storeType",
                "setter": "enums",
                "options": ["TEXT", "NUMBER", "DATE", "TIME", "BOOLEAN", "OBJECT", "ARRAY"]
            }
        ]
    };
}