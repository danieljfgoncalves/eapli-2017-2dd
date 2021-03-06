/**
 *
 */
package eapli.framework.domain;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 * A simple time period class that does not use Range. does not possess any
 * business logic; it is just a data bag.
 *
 * @author Paulo Gandra Sousa
 *
 */
@Embeddable
public class TimePeriod2 implements Serializable{

    @Temporal(TemporalType.DATE)
    Calendar start;

    @Temporal(TemporalType.DATE)
    Calendar end;

    /**
     * Constructor
     *
     * @param start
     * @param end
     */
    public TimePeriod2(Calendar start, Calendar end) throws IllegalStateException {
        if(start.after(end)){
            throw new IllegalStateException("End date cannot finish before start date");
        }
        this.start = start;
        this.end = end;
    }

    protected TimePeriod2() {
    }

    public Calendar start() {
	return this.start;
    }

    public Calendar end() {
        return this.end;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.start);
        hash = 17 * hash + Objects.hashCode(this.end);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimePeriod2 other = (TimePeriod2) obj;
        if (!Objects.equals(this.start, other.start)) {
            return false;
        }
        if (!Objects.equals(this.end, other.end)) {
            return false;
        }
        return true;
    }
    
    
}
