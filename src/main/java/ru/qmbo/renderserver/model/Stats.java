package ru.qmbo.renderserver.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * Stats
 *
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 24.11.2022
 */
@Accessors(chain = true)
@Data
public class Stats implements Comparable<Stats> {
    private int id;
    private String status;
    private Date date;

    @Override
    public int compareTo(Stats o) {
        return this.date.compareTo(o.getDate());
    }
}
