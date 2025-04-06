package io.github.cloudstars.lowcode.bpm.vendor.query;

import java.util.Date;

public interface TaskInfoQuery<T extends TaskInfoQuery<?, ?>, V extends TaskInfo> extends Query<T, V> {
    T taskId(String var1);

    T taskName(String var1);

    T taskNameLike(String var1);

    T taskDescription(String var1);

    T taskDescriptionLike(String var1);

    T taskAssignee(String var1);

    T taskOwner(String var1);

    T taskCreatedOn(Date var1);

    T taskCreatedBefore(Date var1);

    T taskCreatedAfter(Date var1);

}
