package net.cf.object.engine.oql.infos;

import net.cf.form.repository.sql.ast.expr.SqlExpr;
import net.cf.object.engine.object.XObject;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractOqlInfos {

    /**
     * 用于识别批量参数MapList中每一个Map参数在List中的索引位置的标识，用于主表往子表添加主表记录ID时识别对应的序号
     */
    public static final String PARAM_INDEX = "__PARAM_INDEX__";

    /**
     * 解析后的主表模型
     */
    protected XObject resolvedMasterObject;

    /**
     * 解析后的子表模型列表
     */
    private List<XObject> resolvedDetailObjects;

    /**
     * 主表的主键字段的值（可能是常量SqlValuableExpr，也可能是变量SqlVariableRefExpr、SqlListExpr)
     */
    private SqlExpr mainPrimaryIdExpr;

    public XObject getResolvedMasterObject() {
        return resolvedMasterObject;
    }

    public void setResolvedMasterObject(XObject resolvedMasterObject) {
        this.resolvedMasterObject = resolvedMasterObject;
    }

    public List<XObject> getResolvedDetailObjects() {
        return resolvedDetailObjects;
    }

    public void setResolvedDetailObjects(List<XObject> resolvedDetailObjects) {
        this.resolvedDetailObjects = resolvedDetailObjects;
    }

    public void addResolvedDetailObject(XObject resolvedDetailObject) {
        if (this.resolvedDetailObjects == null) {
            this.resolvedDetailObjects = new ArrayList<>();
        }

        for (XObject object : resolvedDetailObjects) {
            if (object.getName().equals(resolvedDetailObject.getName())) {
                return;
            }
        }

        this.resolvedDetailObjects.add(resolvedDetailObject);
    }


    public SqlExpr getMainPrimaryIdExpr() {
        return mainPrimaryIdExpr;
    }

    public void setMainPrimaryIdExpr(SqlExpr mainPrimaryIdExpr) {
        this.mainPrimaryIdExpr = mainPrimaryIdExpr;
    }
}
