import re

filepath = r'd:\DnD-app\dnd-frontend\src\pages\AccountPage\AccountPage.tsx'
with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

if 'useTranslation' not in content:
    content = content.replace('import React', 'import { useTranslation } from "react-i18next";\nimport React')
    content = content.replace('const AccountPage = () => {', 'const AccountPage = () => {\n  const { t } = useTranslation();')

content = content.replace('>No avatar<', '>{t("account.noAvatar")}<')
content = content.replace('>Nickname<', '>{t("account.nickname")}<')
content = content.replace('>E-mail<', '>{t("account.email")}<')
content = content.replace('>Password<', '>{t("account.password")}<')

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)

filepath = r'd:\DnD-app\dnd-frontend\src\pages\DetailPage\ClassDetail.tsx'
with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

if 'useTranslation' not in content:
    content = content.replace('import React', 'import { useTranslation } from "react-i18next";\nimport React')
    content = content.replace('({ data }) => {', '({ data }) => {\n  const { t } = useTranslation();')

content = content.replace('>Class Basics<', '>{t("detail.classBasics")}<')
content = content.replace('Hit Die: d', '{t("detail.hitDie")}: d')
content = content.replace('Proficiency choices: ', '{t("detail.proficiencyChoices")}: ')
content = content.replace('>Proficiencies<', '>{t("detail.proficiencies")}<')
content = content.replace('>Saving Throws<', '>{t("detail.savingThrows")}<')
content = content.replace('>Starting Equipment<', '>{t("detail.startingEquipment")}<')

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)

filepath = r'd:\DnD-app\dnd-frontend\src\pages\AsyncCategoryPage\AsyncCategoryPage.tsx'
with open(filepath, 'r', encoding='utf-8') as f:
    content = f.read()

if 'useTranslation' not in content:
    content = content.replace('import React', 'import { useTranslation } from "react-i18next";\nimport React')
    content = content.replace('const AsyncCategoryPage: React.FC = () => {', 'const AsyncCategoryPage: React.FC = () => {\n  const { t } = useTranslation();')
    
content = content.replace('Failed to load details.', 'detail.errorLoading') # this might be in state, leave it
content = content.replace('Please try again later or choose a different category.', '{t("detail.pleaseTryAgain")}')

with open(filepath, 'w', encoding='utf-8') as f:
    f.write(content)

print('Updated translations in components')
