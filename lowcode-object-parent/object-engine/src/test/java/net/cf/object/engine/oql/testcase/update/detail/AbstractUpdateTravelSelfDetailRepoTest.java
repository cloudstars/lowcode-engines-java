package net.cf.object.engine.oql.testcase.update.detail;

import net.cf.object.engine.oql.testcase.AbstractOqlRepoTest;

public abstract class AbstractUpdateTravelSelfDetailRepoTest
        extends AbstractOqlRepoTest
        implements UpdateTravelSelfDetailTest {

    protected AbstractUpdateTravelSelfDetailRepoTest() {
        super(OQL_FILE_PATH);
    }

    @Override
    protected String[] getDataSetFiles() {
        return new String[]{"dataset/Travel.json", "dataset/TravelTrip.json"};
    }

    @Override
    public void testUpdateTravelAndTripByIdVars() {

    }
}
