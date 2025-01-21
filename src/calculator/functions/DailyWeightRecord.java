package calculator.functions;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics2D;

public class DailyWeightRecord {
    private List<WeightEntry> weightEntries;

    public DailyWeightRecord() {
        this.weightEntries = new ArrayList<>();
    }

    public void addWeight(double weight, String date) {
        weightEntries.add(new WeightEntry(weight, date));
    }

    public void deleteWeight(String date) {
        boolean found = false;
        for (int i = 0; i < weightEntries.size(); i++) {
            if (weightEntries.get(i).getDate().equals(date)) {
                weightEntries.remove(i);
                found = true;
                break;
            }
        }
    }
    public List<Double> getWeightList() {
        return weightEntries.stream()
                .sorted(Comparator.comparing(WeightEntry::getDate))
                .map(WeightEntry::getWeight)
                .collect(Collectors.toList());
    }
    public BufferedImage generateTrendImage() {
        int width = 600;
        int height = 500;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        g2d.setColor(Color.BLACK);
        g2d.drawLine(100, height - 100, width - 100, height - 100);
        g2d.drawLine(100, height - 100, 100, 50);

        if (weightEntries.isEmpty()) {
            g2d.drawString("No data available", width / 2 - 50, height / 2);
            g2d.dispose();
            return image;
        }

        // Sort entries by date
        List<WeightEntry> sortedEntries = weightEntries.stream()
                .sorted(Comparator.comparing(WeightEntry::getDate))
                .collect(Collectors.toList());

        double minWeight = sortedEntries.stream().mapToDouble(WeightEntry::getWeight).min().orElse(0);
        double maxWeight = sortedEntries.stream().mapToDouble(WeightEntry::getWeight).max().orElse(1);
        int minWeightInt = (int) Math.floor(minWeight);
        int maxWeightInt = (int) Math.ceil(maxWeight);

        int plotWidth = width - 200;
        int plotHeight = height - 150;
        int pointCount = sortedEntries.size();
        int xStep = plotWidth / (pointCount - 1);

        for (int i = 0; i < pointCount; i++) {
            String date = sortedEntries.get(i).getDate();
            int x = 100 + i * xStep;
            g2d.drawString(date, x - 30, height - 70);
        }
        
        int prevX = -1, prevY = -1;
        for (int i = 0; i < pointCount; i++) {
            WeightEntry entry = sortedEntries.get(i);
            int x = 100 + i * xStep;
            int y = height - 100 - (int) ((entry.getWeight() - minWeight) / (maxWeight - minWeight) * plotHeight);

            g2d.setColor(Color.BLUE);
            g2d.fillOval(x - 3, y - 3, 6, 6);

            if (prevX != -1 && prevY != -1) {
                g2d.setColor(Color.BLACK);
                g2d.drawLine(prevX, prevY, x, y);
            }

            prevX = x;
            prevY = y;
        }

        g2d.dispose();
        return image;
    }
    class WeightEntry {
        private double weight;
        private String date;

        public WeightEntry(double weight, String date) {
            this.weight = weight;
            this.date = date;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }

        public String getDate() {
            return date;
        }
    }
}
