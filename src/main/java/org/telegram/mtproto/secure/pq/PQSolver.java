package org.telegram.mtproto.secure.pq;

import java.math.BigInteger;

/**
 * Created by Ruben Bermudez on 12.02.14.
 */
public class PQSolver {
    private static PQImplementation currentImplementation = new PQLopatin();

    private PQSolver() {

    }

    public static void setCurrentImplementation(PQImplementation implementation) {
        currentImplementation = implementation;
    }

    public static BigInteger solvePq(BigInteger src) {
        return new BigInteger("" + currentImplementation.findDivider(src.longValue()));
    }
}
