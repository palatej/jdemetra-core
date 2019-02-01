/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demetra.regarima;

import demetra.data.DataBlock;
import demetra.maths.linearfilters.BackFilter;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author palatej
 */
public class RegArimaUtilityTest {
    
    public RegArimaUtilityTest() {
    }

    @Test
    public void testMeanRegression() {
        BackFilter d = RegArimaUtility.differencingFilter(12, 2, 1);
        double[] var = RegArimaUtility.meanRegressionVariable(d, 100);
        DataBlock out=DataBlock.make(100-d.getDegree());
        d.apply(DataBlock.ofInternal(var), out);
        assertTrue(out.isConstant(1));
    }
    
}