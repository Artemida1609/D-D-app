package mate.academy.jvteamproject.importer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

@Service
public class UniversalReferenceFixer {

    public void fix(Object obj) {
        if (obj == null) {
            return;
        }

        if (!(obj instanceof Map) && !(obj instanceof List)) {
            fixEntityUrl(obj);
            fixEntityFields(obj);
            return;
        }

        if (obj instanceof Map<?, ?> map) {
            fixMapSafe(map);
            return;
        }

        if (obj instanceof List<?> list) {
            fixListSafe(list);
        }
    }

    private void fixEntityFields(Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value == null) {
                    continue;
                }

                if (value instanceof Map<?, ?> map) {
                    Map<String, Object> fixed = fixMapSafe(map);
                    field.set(obj, fixed);
                } else if (value instanceof List<?> list) {
                    List<Object> fixed = fixListSafe(list);
                    field.set(obj, fixed);
                }
                if (field.getName().equals("classLevels")) {
                    Field indexField;
                    try {
                        indexField = obj.getClass().getDeclaredField("originalIndex");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    indexField.setAccessible(true);
                    String classIndex = (String) indexField.get(obj);

                    field.set(obj, "/api/classes/" + classIndex);
                }

                if (field.getName().equals("subclassLevels")) {

                    Field indexField;
                    try {
                        indexField = obj.getClass().getDeclaredField("originalIndex");
                    } catch (NoSuchFieldException e) {
                        throw new RuntimeException(e);
                    }
                    indexField.setAccessible(true);
                    String subclassIndex = (String) indexField.get(obj);

                    field.set(obj, "/api/subclasses/" + subclassIndex);
                }

            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private List<Object> fixListSafe(List<?> list) {
        List<Object> newList = new ArrayList<>();
        for (Object item : list) {
            newList.add(fixRecursive(item));
        }
        return newList;
    }

    private Map<String, Object> fixMapSafe(Map<?, ?> map) {
        Map<String, Object> newMap = new HashMap<>();

        for (var entry : map.entrySet()) {
            String key = entry.getKey().toString();
            Object val = entry.getValue();

            if ("url".equals(key) && val instanceof String url) {
                newMap.put(key, fixUrl(url));
            } else {
                newMap.put(key, fixRecursive(val));
            }
        }

        if (newMap.containsKey("index") && newMap.containsKey("url")) {
            String index = (String) newMap.get("index");
            String url = (String) newMap.get("url");
            String entity = detectEntity(url);
            if (entity != null && index != null) {
                newMap.put("url", "/api/" + entity + "/" + index);
            }
        }

        return newMap;
    }

    private Object fixRecursive(Object value) {
        if (value == null) {
            return null;
        }

        if (value instanceof Map<?, ?> map) {
            return fixMapSafe(map);
        }

        if (value instanceof List<?> list) {
            return fixListSafe(list);
        }

        return value;
    }

    private String fixUrl(String url) {
        if (url == null) {
            return null;
        }

        String entity = detectEntity(url);
        if (entity == null) {
            return url;
        }

        String index = url.substring(url.lastIndexOf("/") + 1);

        return "/api/" + entity + "/" + index;
    }

    private void fixEntityUrl(Object obj) {
        try {
            Field urlField = obj.getClass().getDeclaredField("url");
            urlField.setAccessible(true);

            String oldUrl = (String) urlField.get(obj);

            if (isApiEntityUrl(oldUrl)) {
                String newUrl = fixUrl(oldUrl);
                urlField.set(obj, newUrl);
            }

        } catch (NoSuchFieldException e) {
            throw new NoSuchElementException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isApiEntityUrl(String url) {
        if (url == null) {
            return false;
        }

        if (url.startsWith("/api/images/")) {
            return false;
        }

        return url.startsWith("/api/");
    }

    private String detectEntity(String url) {

        if (url.contains("/levels/")) {
            return "levels";
        }

        if (url.contains("/ability-scores/")) {
            return "ability-scores";
        }
        if (url.contains("/classes/")) {
            return "classes";
        }
        if (url.contains("/conditions/")) {
            return "conditions";
        }
        if (url.contains("/damage-types/")) {
            return "damage-types";
        }
        if (url.contains("/equipment/")) {
            return "equipments";
        }
        if (url.contains("/equipment-categories/")) {
            return "equipment-categories";
        }
        if (url.contains("/features/")) {
            return "features";
        }
        if (url.contains("/languages/")) {
            return "languages";
        }
        if (url.contains("/magic-items/")) {
            return "magic-items";
        }
        if (url.contains("/magic-schools/")) {
            return "magic-schools";
        }
        if (url.contains("/monsters/")) {
            return "monsters";
        }
        if (url.contains("/proficiencies/")) {
            return "proficiencies";
        }
        if (url.contains("/races/")) {
            return "races";
        }
        if (url.contains("/rules/")) {
            return "rules";
        }
        if (url.contains("/rule-sections/")) {
            return "rule-sections";
        }
        if (url.contains("/skills/")) {
            return "skills";
        }
        if (url.contains("/spells/")) {
            return "spells";
        }
        if (url.contains("/subclasses/")) {
            return "subclasses";
        }
        if (url.contains("/subraces/")) {
            return "subraces";
        }
        if (url.contains("/traits/")) {
            return "traits";
        }
        if (url.contains("/weapon-properties/")) {
            return "weapon-properties";
        }

        return null;
    }
}
