package iuliia.questionsservice;

import java.util.Arrays;

class Questions {
    private Question[] items;

    public Question[] getItems() {
        return items;
    }

    public void setItems(Question[] items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
