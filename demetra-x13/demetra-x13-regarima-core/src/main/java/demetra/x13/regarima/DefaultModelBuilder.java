/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demetra.x13.regarima;

import demetra.information.InformationSet;
import jdplus.regsarima.regular.IModelBuilder;
import jdplus.regsarima.regular.ModelDescription;
import demetra.timeseries.TsData;

/**
 *
 * @author Jean Palate <jean.palate@nbb.be>
 */
class DefaultModelBuilder implements IModelBuilder {

    public DefaultModelBuilder() {
    }

    @Override
    public ModelDescription build(TsData series, InformationSet log) {
        ModelDescription model=new ModelDescription(series, null);
        model.setAirline(series.getAnnualFrequency()>1);
        model.setMean(true);
        return model;
    }
    
}