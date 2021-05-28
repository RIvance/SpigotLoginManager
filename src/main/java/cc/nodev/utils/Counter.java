package cc.nodev.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Counter {
    @Getter @Setter private int value;

    public boolean decrease() {
        return (this.value--) == 0;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
