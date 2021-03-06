/*
 * Copyright 2013 National Bank of Belgium
 *
 * Licensed under the EUPL, Version 1.1 or – as soon they will be approved 
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * http://ec.europa.eu/idabc/eupl
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and 
 * limitations under the Licence.
 */
package ec.tstoolkit.modelling.arima.tramo;

import ec.tstoolkit.algorithm.IProcResults;
import ec.tstoolkit.design.Development;
import ec.tstoolkit.maths.realfunctions.IFunctionMinimizer;
import ec.tstoolkit.maths.realfunctions.ProxyMinimizer;
import ec.tstoolkit.maths.realfunctions.levmar.LevenbergMarquardtMethod;
import ec.tstoolkit.modelling.arima.ModellingContext;
import ec.tstoolkit.sarima.estimation.GlsSarimaMonitor;
import ec.tstoolkit.sarima.estimation.SarimaInitializer;

/**
 *
 * @author Jean Palate
 */
@Development(status = Development.Status.Preliminary)
public abstract class AbstractTramoModule {

    protected IFunctionMinimizer minimizer;

    protected AbstractTramoModule() {
        //minimizer = new ProxyMinimizer(new TramoMarquardt());
        minimizer = new ProxyMinimizer(new LevenbergMarquardtMethod());
        minimizer.setConvergenceCriterion(1e-5);
        //minimizer = new ProxyMinimizer(new DogLegMethod());
    }

    /**
     * @return the minimizer
     */
    public IFunctionMinimizer getMinimizer() {
        return minimizer;
    }

    public GlsSarimaMonitor getMonitor() {
        GlsSarimaMonitor monitor = new GlsSarimaMonitor();
        monitor.setMinimizer(minimizer.exemplar());
        return monitor;
    }

    public GlsSarimaMonitor getMonitor(SarimaInitializer initializer)
     {
        GlsSarimaMonitor monitor = new GlsSarimaMonitor(initializer);
        monitor.setMinimizer(minimizer.exemplar());
         return monitor;
    }

    /**
     * @param minimizer the minimizer to set
     */
    public void setMinimizer(IFunctionMinimizer minimizer) {
        this.minimizer = minimizer;
    }
    
    public void setPrecision(double eps){
        minimizer.setConvergenceCriterion(eps);
    }
    
    public static void transferLogs(ModellingContext cxt, ModellingContext context) {
        if (context.processingLog != null && cxt.processingLog != null && !cxt.processingLog.isEmpty()) {
            context.processingLog.addAll(cxt.processingLog);
        }
    }

    public static void transferLogs(IProcResults rslts, ModellingContext context) {
        if (context.processingLog != null) {
            context.processingLog.addAll(rslts.getProcessingInformation());
        }
    }
}
