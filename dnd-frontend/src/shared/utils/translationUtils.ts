export const transformTranslatedData = (data: any, lang: string): any => {
  if (!data || typeof data !== "object") return data;
  
  if (Array.isArray(data)) {
    return data.map(item => transformTranslatedData(item, lang));
  }
  
  const result = { ...data };
  
  if (lang === "UA") {
    // Collect keys that end with 'Ua'
    const keys = Object.keys(result);
    for (const key of keys) {
      if (key.endsWith("Ua")) {
        const baseKey = key.slice(0, -2);
        // If Ua field has a value, override the base field
        if (result[key] !== null && result[key] !== undefined && result[key] !== "") {
           result[baseKey] = result[key];
        }
      } else if (typeof result[key] === "object") {
        result[key] = transformTranslatedData(result[key], lang);
      }
    }
  }
  
  return result;
};
