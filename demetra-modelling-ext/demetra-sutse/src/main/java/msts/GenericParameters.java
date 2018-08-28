/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package msts;

import demetra.data.DoubleReader;
import demetra.maths.functions.IParametersDomain;

/**
 *
 * @author palatej
 */
public class GenericParameters implements IMstsParametersBlock {

    private final double[] defParameters, parameters;
    private final IParametersDomain domain;

    public GenericParameters(IParametersDomain domain, double[] defParameters, double[] parameters) {
        this.domain = domain;
        this.defParameters = defParameters;
        this.parameters = parameters;
    }

    @Override
    public boolean isFixed() {
        return parameters != null;
    }

    @Override
    public int decode(DoubleReader reader, double[] buffer, int pos) {
        int n = defParameters.length;
        if (parameters == null) {
            for (int i = 0; i < n; ++i) {
                buffer[pos++] = reader.next();
            }
        } else {
            for (int i = 0; i < n; ++i) {
                buffer[pos++] = parameters[i];
            }
        }
        return pos;
    }

    @Override
    public int encode(DoubleReader reader, double[] buffer, int pos) {
        if (parameters == null) {
            int n = defParameters.length;
            for (int i = 0; i < n; ++i) {
                buffer[pos++] = reader.next();
            }
        }
        return pos;
    }

    @Override
    public IParametersDomain getDomain() {
        return domain;
    }

    @Override
    public int fillDefault(double[] buffer, int pos) {
        if (parameters == null) {
            System.arraycopy(defParameters, 0, buffer, pos, defParameters.length);
            return pos + defParameters.length;
        } else {
            return pos;
        }
    }
}
