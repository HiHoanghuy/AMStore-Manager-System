package custom.lineChart;

import java.util.ArrayList;

public class ModelChart {

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ArrayList<Double> getValues() {
        return values;
    }

    public void setValues(ArrayList<Double> values) {
        this.values = values;
    }

    public ModelChart(String label, ArrayList<Double> values) {
        this.label = label;
        this.values = values;
    }

    public ModelChart() {
    }

    private String label;
    private ArrayList<Double>  values;

    public double getMaxValues() {
        double max = 0;
        for (double v : values) {
            if (v > max) {
                max = v;
            }
        }
        return max;
    }
}
