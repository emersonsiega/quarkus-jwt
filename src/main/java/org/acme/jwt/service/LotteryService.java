package org.acme.jwt.service;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.jwt.Claims;
import org.eclipse.microprofile.jwt.JsonWebToken;

@RequestScoped
public class LotteryService {

    @Inject
    JsonWebToken jwt;

    /**
     * Produces a random lottery result.
     * 
     * @return String with 6 numbers
     */
    public String winners() {
        int remaining = 6;
        ArrayList<Integer> numbers = new ArrayList<>();

        // If the JWT contains a birthdate claim, use the day of the month as a pick
        if (jwt.containsClaim(Claims.birthdate.name())) {
            String bdayString = jwt.getClaim(Claims.birthdate.name());
            LocalDate bday = LocalDate.parse(bdayString);
            numbers.add(bday.getDayOfMonth());
            remaining--;
        }

        // Fill remaining picks with random numbers
        while (remaining > 0) {
            int pick = (int) Math.rint(64 * Math.random() + 1);
            numbers.add(pick);
            remaining--;
        }

        return numbers.toString();
    }
}