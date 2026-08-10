import re

import json

with open(r'd:\DnD-app\dnd-frontend\src\locales\en\translation.json', 'r', encoding='utf-8') as f:
    en_json = json.load(f)

en_json['ui'] = {
    "search": "Search",
    "filters": "Filters"
}

with open(r'd:\DnD-app\dnd-frontend\src\locales\en\translation.json', 'w', encoding='utf-8') as f:
    json.dump(en_json, f, ensure_ascii=False, indent=2)

with open(r'd:\DnD-app\dnd-frontend\src\locales\ua\translation.json', 'r', encoding='utf-8') as f:
    ua_json = json.load(f)

ua_json['ui'] = {
    "search": "Пошук",
    "filters": "Фільтри"
}

with open(r'd:\DnD-app\dnd-frontend\src\locales\ua\translation.json', 'w', encoding='utf-8') as f:
    json.dump(ua_json, f, ensure_ascii=False, indent=2)

import os

files_to_update = [
    r'd:\DnD-app\dnd-frontend\src\shared\ui\Header\Header.tsx',
    r'd:\DnD-app\dnd-frontend\src\shared\ui\SearchFilters\SearchFilters.tsx',
    r'd:\DnD-app\dnd-frontend\src\shared\ui\SideBar\SideBar.tsx',
    r'd:\DnD-app\dnd-frontend\src\pages\CategoryPage\CategoryPage.tsx',
    r'd:\DnD-app\dnd-frontend\src\pages\AsyncCategoryPage\AsyncCategoryPage.tsx',
]

def add_use_translation(content):
    if 'useTranslation' not in content:
        content = content.replace('import React', 'import { useTranslation } from "react-i18next";\nimport React')
        content = re.sub(r'const (\w+)[^=]*= \([^)]*\) => {', r'const \1 = (\g<1>) => {\n  const { t } = useTranslation();', content)
        # also handle case where it's function Some() {
        content = re.sub(r'function (\w+)[^)]*\([^)]*\)[^{]*{', r'function \1() {\n  const { t } = useTranslation();', content)
    return content

for filepath in files_to_update:
    if not os.path.exists(filepath):
        continue
        
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    # Manual replacements where we can be precise
    if 'Header.tsx' in filepath:
        content = content.replace('import { Button }', 'import { useTranslation } from "react-i18next";\nimport { Button }')
        content = content.replace('export const Header = () => {', 'export const Header = () => {\n  const { t } = useTranslation();')
        content = content.replace('Log in', '{t("auth.login")}')
        content = content.replace('Sign up', '{t("auth.signup")}')

    if 'SearchFilters.tsx' in filepath:
        if 'useTranslation' not in content:
            content = content.replace('import { SearchIcon }', 'import { useTranslation } from "react-i18next";\nimport { SearchIcon }')
            content = content.replace('} = useSearchFilters(isSidebar);', '} = useSearchFilters(isSidebar);\n  const { t } = useTranslation();')
        
        content = content.replace('placeholder="Search"', 'placeholder={t("ui.search")}')
        content = content.replace('<span>Search</span>', '<span>{t("ui.search")}</span>')
        content = content.replace('>Filters<', '>{t("ui.filters")}<')

    if 'SideBar.tsx' in filepath:
        if 'useTranslation' not in content:
            content = content.replace('import { SearchIcon }', 'import { useTranslation } from "react-i18next";\nimport { SearchIcon }')
            content = content.replace('} = useSearchFilters(true);', '} = useSearchFilters(true);\n  const { t } = useTranslation();')
        
        content = content.replace('placeholder="Search"', 'placeholder={t("ui.search")}')
        content = content.replace('<span>Search</span>', '<span>{t("ui.search")}</span>')
        content = content.replace('>Search<', '>{t("ui.search")}<')
        content = content.replace('>Filters<', '>{t("ui.filters")}<')
        content = content.replace('sidebar__back-text">Search<', 'sidebar__back-text">{t("ui.search")}<')

    if 'AsyncCategoryPage.tsx' in filepath or 'CategoryPage.tsx' in filepath:
        # Category page titles are often derived from params.category which is in english. 
        # For example: categoryName.replace("-", " ") 
        # I need to translate it using the 	("nav." + category) or something similar.
        # But this might be too complex for a simple script, I will do it with replace tool if needed.
        pass

    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

print("Updated more translations!")
