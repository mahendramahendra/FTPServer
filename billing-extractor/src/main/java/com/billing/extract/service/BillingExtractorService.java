package com.billing.extract.service;

import com.billing.extract.model.BillingInfo;
import com.billing.extract.model.BillingItem;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BillingExtractorService {
    private static final Pattern LINE_PATTERN = Pattern.compile("(.+?)\\s+(\\d+(?:\\.\\d+)?)");

    public BillingInfo extract(MultipartFile file, String prompt) throws IOException {
        List<BillingItem> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher m = LINE_PATTERN.matcher(line);
                if (m.find()) {
                    String description = m.group(1).trim();
                    double value = Double.parseDouble(m.group(2));
                    items.add(new BillingItem(description, value));
                }
            }
        }
        double total = items.stream().mapToDouble(BillingItem::getValue).sum();
        BillingInfo info = new BillingInfo();
        info.setItems(items);
        info.setTotal(total);
        return info;
    }
}
