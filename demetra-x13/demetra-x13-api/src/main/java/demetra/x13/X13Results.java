/*
 * Copyright 2020 National Bank of Belgium
 *
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved 
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/software/page/eupl
 *
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and 
 * limitations under the Licence.
 */
package demetra.x13;

import demetra.arima.SarimaModel;
import demetra.processing.ProcessingLog;
import demetra.timeseries.regression.modelling.GeneralLinearModel;
import demetra.x11.X11Results;
import java.util.List;
import java.util.Map;

/**
 *
 * @author palatej
 */
@lombok.Value
@lombok.Builder
public class X13Results {
    private GeneralLinearModel<SarimaModel> preprocessing;
    private X13Preadjustment preadjustment;
    private X11Results decomposition;
    private X13Finals finals;

    @lombok.Singular
    private Map<String, Object> addtionalResults;
   
    @lombok.Singular
    private List<ProcessingLog.Information> logs;
    
}
