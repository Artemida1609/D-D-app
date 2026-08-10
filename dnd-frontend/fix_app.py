import re

filepath = r'd:\DnD-app\dnd-frontend\src\App.tsx'
with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

if 'useTranslation' not in content:
    content = content.replace('import { BrowserRouter', 'import { useTranslation } from "react-i18next";\nimport { BrowserRouter')
    content = content.replace('export const App = () => {', 'export const App = () => {\n  const { t } = useTranslation();')

# mapping of english title to translation key
title_map = {
    'Species': 'nav.species',
    'Classes': 'nav.classes',
    'Skills': 'nav.skills',
    'Weapons': 'nav.weapons',
    'Armors': 'nav.armor', # wait, nav.armor
    'Gear': 'nav.gear',
    'Spells': 'nav.spells',
    'Schools': 'nav.schools',
    'Bestiary': 'nav.bestiary'
}

for eng, key in title_map.items():
    content = content.replace(f'title="{eng}"', f'title={{t("{key}")}}')

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)

print("Updated App.tsx")
