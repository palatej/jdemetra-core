/*
 * Copyright 2019 National Bank of Belgium.
 *
 * Licensed under the EUPL, Version 1.2 or – as soon they will be approved
 * by the European Commission - subsequent versions of the EUPL (the "Licence");
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 *      https://joinup.ec.europa.eu/software/page/eupl
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package demetra.maths.spi;

import demetra.design.Algorithm;
import demetra.design.ServiceDefinition;
import demetra.maths.Complex;
import demetra.maths.RealPolynomial;
import demetra.util.ServiceLookup;
import java.util.concurrent.atomic.AtomicReference;

/**
 *
 * @author Jean Palate
 */
@lombok.experimental.UtilityClass
public class Polynomials {
    private final AtomicReference<Processor> PROCESSOR = ServiceLookup.firstMutable(Processor.class);
    
    public void setProcessor(Processor processor) {
        PROCESSOR.set(processor);
    }

    public Processor getProcessor() {
        return PROCESSOR.get();
    }
    
    public Complex[] roots(RealPolynomial p){
        return PROCESSOR.get().roots(p);
    }
    
    @ServiceDefinition
    @Algorithm
    public static interface Processor {
        Complex[] roots(RealPolynomial p);
        
        
    }    
    
}