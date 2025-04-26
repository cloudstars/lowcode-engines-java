(options) => {
    return {
        "name": "data-type-text",
        "description": "这是文本数据格式规范",
        "extends": "data-type-common",
        "attributes": [
            {
                "name": "minLength",
                "setter": "number",
                "precision": 0
            },
            {
                "name": "maxLength",
                "setter": "number",
                "precision": 0
            }
        ]
    };
}