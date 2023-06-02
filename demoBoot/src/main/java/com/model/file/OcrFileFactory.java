package com.model.file;


import com.model.contract.OcrEngineOpt;

public class OcrFileFactory {
    private static OcrFileFactory self = null;

    private OcrFileFactory() {
    }

    public static OcrFileFactory GetInstance() {
        if (null == self) {
            self = new OcrFileFactory();
        }
        return self;
    }

    /*public OcrFilePool getOcrFilePoolInstance(OcrEngineOpt opt) {
        OcrFilePool obj = new HexImpl();
        obj.setEngineOpt(opt);
        return obj;
    }*/

    public OcrFilePool getOcrFilePoolInstance(OcrEngineOpt opt) {
        OcrFilePool obj = null;
        switch (opt){
            case TABLE:
                obj = new TableEngineEngineImpl();
                break;
            case INTSIG:
                obj = new IntsigEngineImpl();
                break;
        }
        obj.setEngineOpt(opt);
        return obj;
    }
}
