(options) => {
    if (options.redundant) {
        return [{
            "name": "key",
            "title": "用户编号",
            "dataType": "TEXT"
        }, {
            "name": "name",
            "title": "用户名称",
            "dataType": "TEXT"
        }];
    } else {
        return [];
    }
};