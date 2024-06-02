package vn.esoft.platform.icustomer.entities;

import java.io.Serial;
import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 4286536031236473476L;

    protected abstract void createdAt();
    protected abstract void updatedAt();

}
