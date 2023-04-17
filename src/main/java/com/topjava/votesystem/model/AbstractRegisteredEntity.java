package com.topjava.votesystem.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@MappedSuperclass
// http://stackoverflow.com/questions/594597/hibernate-annotations-which-is-better-field-or-property-access
@Access(AccessType.FIELD)
public abstract class AbstractRegisteredEntity extends AbstractNamedEntity {
    @NotNull
    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()", updatable = false)
    protected LocalDateTime registered = LocalDateTime.now();

    protected AbstractRegisteredEntity() {
    }

    protected AbstractRegisteredEntity(String name, LocalDateTime registered) {
        super(name);
        this.registered = registered;
    }

    public void setRegistered(LocalDateTime registered) {
        this.registered = registered;
    }

    public LocalDateTime getRegistered() {
        return this.registered;
    }

    @Override
    public String toString() {
        return super.toString() + '(' + registered + ')';
    }
}
