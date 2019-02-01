/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demetra.msts;

import demetra.data.Data;
import demetra.data.DataBlock;
import demetra.data.DataBlockIterator;
import demetra.data.MatrixSerializer;
import demetra.maths.MatrixType;
import demetra.maths.matrices.Matrix;
import demetra.ssf.ISsfLoading;
import demetra.ssf.StateStorage;
import demetra.ssf.dk.DkToolkit;
import demetra.ssf.implementations.Loading;
import demetra.ssf.implementations.MultivariateCompositeSsf;
import demetra.arima.ssf.SsfAr;
import demetra.sts.LocalLevel;
import demetra.sts.LocalLinearTrend;
import demetra.arima.ssf.SsfAr2;
import demetra.ssf.multivariate.SsfMatrix;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.junit.Test;

/**
 *
 * @author palatej
 */
public class MstsMonitorTest {

    public MstsMonitorTest() {
    }

    @Test
    public void testSimple() throws URISyntaxException, IOException {

        File file = Data.copyToTempFile(MultivariateCompositeSsf.class.getResource("/bematrix.txt"));
        MatrixType data = MatrixSerializer.read(file, "\t|,");

        Matrix D = Matrix.make(data.getRowsCount(), 4);
        D.column(0).copy(data.column(0));
        D.column(1).copy(data.column(9));
        D.column(2).copy(data.column(2));
        D.column(3).copy(data.column(3));

        DataBlockIterator cols = D.columnsIterator();
        while (cols.hasNext()) {
            DataBlock col = cols.next();
            col.normalize();
        }

        MstsMonitor monitor = MstsMonitor.builder()
                .marginalLikelihood(false)
                .build();
        MstsMapping mapping = new MstsMapping();
        generateU(mapping);
        generateY(mapping);
        generatePicore(mapping);
        generatePi(mapping);
        generateCycle(mapping);
        monitor.process(D, mapping, null);
        System.out.println(monitor.getLikelihood().logLikelihood());
        System.out.println(monitor.fullParameters());
        StateStorage ss = DkToolkit.smooth(monitor.getSsf(), new SsfMatrix(monitor.getData()), true);
        System.out.println(ss.getComponent(monitor.getSsf().componentsPosition()[4]));
    }

    //@Test
    public void testSimpleX() throws URISyntaxException, IOException {

        URI uri = MultivariateCompositeSsf.class.getResource("/bematrix.txt").toURI();
        MatrixType data = MatrixSerializer.read(new File(uri), "\t|,");

        Matrix D = Matrix.make(data.getRowsCount(), 6);
        D.column(0).copy(data.column(0));
        D.column(1).copy(data.column(9));
        D.column(2).copy(data.column(2));
        D.column(3).copy(data.column(3));
        D.column(4).copy(data.column(5));
        D.column(5).copy(data.column(6));
//        D=D.extract(0, 90, 0, 6);

//        DataBlockIterator cols = D.columnsIterator();
//        while (cols.hasNext()) {
//            DataBlock col = cols.next();
//            col.normalize();
//        }
        MstsMapping mapping = new MstsMapping();

        generateU(mapping);
        generateY(mapping);
        generatePicore(mapping);
        generatePi(mapping);
        generateXCycle(mapping);

        MstsMonitor monitor = MstsMonitor.builder()
                .marginalLikelihood(false)
                .precision(1e-12)
                .build();
        System.out.println("X");
        monitor.process(D, mapping, null);
        System.out.println(monitor.getLikelihood().logLikelihood());
        System.out.println(monitor.fullParameters());
        StateStorage ss = DkToolkit.smooth(monitor.getSsf(), new SsfMatrix(monitor.getData()), true);
        System.out.println(ss.getComponent(monitor.getSsf().componentsPosition()[4]));
    }

    //@Test
    public void testSimpleX2() throws URISyntaxException, IOException {

        URI uri = MultivariateCompositeSsf.class.getResource("/bematrix.txt").toURI();
        MatrixType data = MatrixSerializer.read(new File(uri), "\t|,");

        Matrix D = Matrix.make(data.getRowsCount(), 6);
        D.column(0).copy(data.column(0));
        D.column(1).copy(data.column(9));
        D.column(2).copy(data.column(2));
        D.column(3).copy(data.column(3));
        D.column(4).copy(data.column(5));
        D.column(5).copy(data.column(6));

//        DataBlockIterator cols = D.columnsIterator();
//        while (cols.hasNext()) {
//            DataBlock col = cols.next();
//            col.normalize();
//        }
        MstsMapping mapping = new MstsMapping();

        generateU(mapping);
        generateY(mapping);
        generatePicore(mapping);
        generatePi(mapping);
        generateCycle(mapping);
        generateB(mapping);
        generateC(mapping);

        MstsMonitor monitor = MstsMonitor.builder()
                .marginalLikelihood(false)
                .precision(1e-12)
                .build();
        monitor.process(D, mapping, null);
        System.out.println(monitor.getLikelihood().logLikelihood());
        System.out.println(monitor.fullParameters());
        StateStorage ss = DkToolkit.smooth(monitor.getSsf(), new SsfMatrix(monitor.getData()), true);
        System.out.println(ss.getComponent(monitor.getSsf().componentsPosition()[4]));
    }

    //@Test
    public void testSimpleXbis() throws URISyntaxException, IOException {

        URI uri = MultivariateCompositeSsf.class.getResource("/bematrix.txt").toURI();
        MatrixType data = MatrixSerializer.read(new File(uri), "\t|,");

        Matrix D = Matrix.make(data.getRowsCount(), 6);
        D.column(0).copy(data.column(0));
        D.column(1).copy(data.column(9));
        D.column(2).copy(data.column(2));
        D.column(3).copy(data.column(3));
        D.column(4).copy(data.column(5));
        D.column(5).copy(data.column(6));
//        D=D.extract(0, 90, 0, 6);

//        DataBlockIterator cols = D.columnsIterator();
//        while (cols.hasNext()) {
//            DataBlock col = cols.next();
//            col.normalize();
//        }
        MstsMapping mapping = new MstsMapping();

        generateU2(mapping);
        generateY2(mapping);
        generatePicore2(mapping);
        generatePi2(mapping);
        generateB2(mapping);
        generateC2(mapping);
        generateCycle2(mapping);

        MstsMonitor monitor = MstsMonitor.builder()
                .marginalLikelihood(false)
                .precision(1e-12)
                .build();
        monitor.process(D, mapping, null);
        System.out.println("Xbis");
        System.out.println(monitor.getLikelihood().logLikelihood());
        System.out.println(monitor.fullParameters());
        StateStorage ss = DkToolkit.smooth(monitor.getSsf(), new SsfMatrix(monitor.getData()), true);
        System.out.println(ss.getComponent(monitor.getSsf().componentsPosition()[6]+4));
    }
    
    private void generateU(MstsMapping mapping) {
        mapping.add(new VarianceParameter("u_var", 1, true, false));
        mapping.add(new LoadingParameter("u_c"));
        mapping.add(new VarianceParameter("tu_var", true));
        mapping.add((p, builder) -> {
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(p.get(0));
            eq.add(new MultivariateCompositeSsf.Item("tu"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", p.get(1)));
            builder.add("tu", LocalLinearTrend.of(0, p.get(2)))
                    .add(eq);
            return 3;
        });

    }

    private void generateY(MstsMapping mapping) {
        mapping.add(new VarianceParameter("y_var", false));
        mapping.add(new LoadingParameter("y_c"));
        mapping.add(new VarianceParameter("ty_var", true));
        mapping.add((p, builder) -> {
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(p.get(0));
            eq.add(new MultivariateCompositeSsf.Item("ty"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", p.get(1)));
            builder.add("ty", LocalLinearTrend.of(0, p.get(2)))
                    .add(eq);
            return 3;
        });

    }

    private void generatePicore(MstsMapping mapping) {
        mapping.add(new VarianceParameter("picore_var", false));
        mapping.add(new LoadingParameter("picore_c"));
        mapping.add(new VarianceParameter("tpicore_var", true));
        mapping.add((p, builder) -> {
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(p.get(0));
            eq.add(new MultivariateCompositeSsf.Item("tpicore"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", p.get(1), Loading.fromPosition(4)));
            builder.add("tpicore", LocalLevel.of(p.get(2)))
                    .add(eq);
            return 3;
        });
    }

    private void generatePi(MstsMapping mapping) {
        mapping.add(new VarianceParameter("pi_var", false));
        mapping.add(new LoadingParameter("pi_c"));
        mapping.add(new VarianceParameter("tpi_var", true));
        mapping.add((p, builder) -> {
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(p.get(0));
            eq.add(new MultivariateCompositeSsf.Item("tpi"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", p.get(1)));
            builder.add("tpi", LocalLevel.of(p.get(2)))
                    .add(eq);
            return 3;
        });

    }

    private void generateCycle(MstsMapping mapping) {
        mapping.add(new ArParameters("ar", new double[]{1, -.5}, false));
        mapping.add(new VarianceParameter("ar_var", 1, true, false));
        mapping.add((p, builder) -> {
            double c1 = p.get(0), c2 = p.get(1), v = p.get(2);
            builder.add("cycle", SsfAr.of(new double[]{c1, c2}, v, 5));
            return 3;
        });

    }

    private void generateXCycle(MstsMapping mapping) {
        mapping.add(new ArParameters("ar", new double[]{1, -.5}, false));
        mapping.add(new VarianceParameter("ar_var", 1, true, false));
        mapping.add(new LoadingParameter("b_c"));
        mapping.add(new VarianceParameter("b_var", false));
        mapping.add(new LoadingParameter("c_c"));
        mapping.add(new VarianceParameter("c_var", false));
        mapping.add((p, builder) -> {
            double c1 = p.get(0), c2 = p.get(1), v = p.get(2);
            double b1 = p.get(3), v1 = p.get(4);
            double b2 = p.get(5), v2 = p.get(6);
            ISsfLoading pl = Loading.from(new int[]{0, 1}, new double[]{b1 * c1, b1 * c2});
            MultivariateCompositeSsf.Equation eq1 = new MultivariateCompositeSsf.Equation(v1);
            eq1.add(new MultivariateCompositeSsf.Item("tb"));
            eq1.add(new MultivariateCompositeSsf.Item("cycle", 1, pl));
            double c12 = c1 * c1, c13 = c12 * c1, c14 = c13 * c1, c22 = c2 * c2;
            double d1 = c1 + c12 + c13 + c14 + c2 + c22 + 2 * c1 * c2 + 3 * c12 * c2;
            double d2 = c2 + c1 * c2 + c22 + c12 * c2 + 2 * c1 * c22 + c13 * c2;
            pl = Loading.from(new int[]{0, 1}, new double[]{b2 * d1, b2 * d2});
            MultivariateCompositeSsf.Equation eq2 = new MultivariateCompositeSsf.Equation(v2);
            eq2.add(new MultivariateCompositeSsf.Item("tc"));
            eq2.add(new MultivariateCompositeSsf.Item("cycle", 1, pl));
            builder.add("cycle", SsfAr.of(new double[]{c1, c2}, v, 5))
                    .add("tb", LocalLevel.of(0))
                    .add("tc", LocalLevel.of(0))
                    .add(eq1)
                    .add(eq2);

            return 7;
//            builder.add("cycle", AR.componentOf(new double[]{c1, c2}, v, 5));
//            return 3;
        });

    }

    private void generateB(MstsMapping mapping) {
        mapping.add(new VarianceParameter("bs_var", false));
        mapping.add(new LoadingParameter("bs1_c"));
        mapping.add(new LoadingParameter("bs2_c"));
        mapping.add((p, builder) -> {
            double v = p.get(0), a1 = p.get(1), a2 = p.get(2);
            ISsfLoading pl = Loading.from(new int[]{0, 1}, new double[]{a1, a2});
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(v);
            eq.add(new MultivariateCompositeSsf.Item("tb"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", a1, Loading.fromPosition(0)));
            eq.add(new MultivariateCompositeSsf.Item("cycle", a2, Loading.fromPosition(1)));
            builder.add("tb", LocalLevel.of(0))
                    .add(eq);
            return 3;
        });
    }

    private void generateC(MstsMapping mapping) {
        mapping.add(new VarianceParameter("cs_var", false));
        mapping.add(new LoadingParameter("cs1_c"));
        mapping.add(new LoadingParameter("cs2_c"));
        mapping.add((p, builder) -> {
            double v = p.get(0), a1 = p.get(1), a2 = p.get(2);
            ISsfLoading pl = Loading.from(new int[]{0, 1}, new double[]{a1, a2});
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(v);
            eq.add(new MultivariateCompositeSsf.Item("tc"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", 1, pl));
            builder.add("tc", LocalLevel.of(0))
                    .add(eq);
            return 4;
        });
    }

    private void generateU2(MstsMapping mapping) {
        mapping.add(new VarianceParameter("u_var", 1, true, false));
        mapping.add(new LoadingParameter("u_c"));
        mapping.add(new VarianceParameter("tu_var", true));
        mapping.add((p, builder) -> {
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(p.get(0));
            eq.add(new MultivariateCompositeSsf.Item("tu"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", p.get(1), Loading.fromPosition(4)));
            builder.add("tu", LocalLinearTrend.of(0, p.get(2)))
                    .add(eq);
            return 3;
        });

    }

    private void generateY2(MstsMapping mapping) {
        mapping.add(new VarianceParameter("y_var", false));
        mapping.add(new LoadingParameter("y_c"));
        mapping.add(new VarianceParameter("ty_var", true));
        mapping.add((p, builder) -> {
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(p.get(0));
            eq.add(new MultivariateCompositeSsf.Item("ty"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", p.get(1), Loading.fromPosition(4)));
            builder.add("ty", LocalLinearTrend.of(0, p.get(2)))
                    .add(eq);
            return 3;
        });

    }

    private void generatePicore2(MstsMapping mapping) {
        mapping.add(new VarianceParameter("picore_var", false));
        mapping.add(new LoadingParameter("picore_c"));
        mapping.add(new VarianceParameter("tpicore_var", true));
        mapping.add((p, builder) -> {
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(p.get(0));
            eq.add(new MultivariateCompositeSsf.Item("tpicore"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", p.get(1), Loading.fromPosition(0)));
            builder.add("tpicore", LocalLevel.of(p.get(2)))
                    .add(eq);
            return 3;
        });
    }

    private void generatePi2(MstsMapping mapping) {
        mapping.add(new VarianceParameter("pi_var", false));
        mapping.add(new LoadingParameter("pi_c"));
        mapping.add(new VarianceParameter("tpi_var", true));
        mapping.add((p, builder) -> {
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(p.get(0));
            eq.add(new MultivariateCompositeSsf.Item("tpi"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", p.get(1), Loading.fromPosition(4)));
            builder.add("tpi", LocalLevel.of(p.get(2)))
                    .add(eq);
            return 3;
        });

    }

    private void generateCycle2(MstsMapping mapping) {
        mapping.add(new ArParameters("ar", new double[]{1, -.5}, false));
        mapping.add(new VarianceParameter("ar_var", 1, true, false));
        mapping.add((p, builder) -> {
            double c1 = p.get(0), c2 = p.get(1), v = p.get(2);
            builder.add("cycle", SsfAr2.of(new double[]{c1, c2}, v, 4, 4));
            return 3;
        });

    }

        private void generateB2(MstsMapping mapping) {
        mapping.add(new VarianceParameter("bs_var", false));
        mapping.add(new LoadingParameter("bs_c"));
        mapping.add((p, builder) -> {
            double v = p.get(0), a1 = p.get(1);
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(v);
            eq.add(new MultivariateCompositeSsf.Item("tb"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", a1, Loading.fromPosition(5)));
            builder.add("tb", LocalLevel.of(0))
                    .add(eq);
            return 2;
        });
    }

    private void generateC2(MstsMapping mapping) {
        mapping.add(new VarianceParameter("cs_var", false));
        mapping.add(new LoadingParameter("cs_c"));
        mapping.add((p, builder) -> {
            double v = p.get(0), a1 = p.get(1);
            MultivariateCompositeSsf.Equation eq = new MultivariateCompositeSsf.Equation(v);
            eq.add(new MultivariateCompositeSsf.Item("tc"));
            eq.add(new MultivariateCompositeSsf.Item("cycle", 1, Loading.from(new int[]{5,6,7,8},
                    new double[]{a1,a1,a1,a1})));
            builder.add("tc", LocalLevel.of(0))
                    .add(eq);
            return 2;
        });
    }


}