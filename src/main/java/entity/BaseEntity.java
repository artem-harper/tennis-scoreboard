package entity;

import java.io.Serializable;

public interface BaseEntity<Key extends Serializable> {

    Key getId();
}
