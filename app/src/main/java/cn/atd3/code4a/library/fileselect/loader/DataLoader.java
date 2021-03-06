package cn.atd3.code4a.library.fileselect.loader;



import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;

import cn.atd3.code4a.library.fileselect.constant.Constant;
import cn.atd3.code4a.library.fileselect.factory.DataFactory;
import cn.atd3.code4a.library.fileselect.factory.DataType;
import cn.atd3.code4a.library.fileselect.model.DataModel;


/**
 * Created by admin on 2016/11/22.
 */
public class DataLoader extends AsyncTaskLoader<DataModel> {


    private DataModel dataModel;

    public DataLoader(Context context, Bundle bundle) {
        super(context);
        DataType dataType = (DataType) bundle.getSerializable(Constant.DATA_TYPE_KEY);
        dataModel = DataFactory.getInstance().createDataModel(context,dataType,bundle);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public DataModel loadInBackground() {
        dataModel.loadData();
        return dataModel;
    }
}
