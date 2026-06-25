# D&D Wiki Frontend

Це фронтенд-каркас для D&D Wiki у стилі Feature-Sliced Design. Проєкт залишений навмисно простим на рівні MVP: сторінки збирають UI, сутності зберігають доменну модель, фічі містять поведінку користувача, а спільний шар не знає нічого про конкретний предметний домен.

## Стек

- React 19
- TypeScript
- Vite
- React Router
- TanStack Query
- Axios
- Tailwind CSS
- Zustand

## Архітектура

Поточна структура в `src/`:

```txt
src/
├── app/
│   ├── router/
│   ├── providers/
│   └── layouts/
├── pages/
├── features/
├── entities/
└── shared/
```

### `app/`

Глобальна точка композиції застосунку.

- `app/router/` містить усі маршрути в одному місці.
- `app/providers/` збирає глобальні провайдери, наприклад `QueryClientProvider`.
- `app/layouts/` містить загальний shell, наприклад `MainLayout`.

### `pages/`

Сторінки на рівні роутів. Вони не мають знати, як саме отримуються дані або як реалізована бізнес-логіка. Їхня задача - зібрати вже готові блоки.

У цьому каркасі є:

- `HomePage`
- `ClassesPage`
- `RacesPage`
- `SpellsPage`
- `MonstersPage`
- `SearchPage`

### `entities/`

Найважливіший шар для доменних сутностей:

- `entities/spell`
- `entities/class`
- `entities/race`
- `entities/monster`

Кожна сутність має власний набір типів, API-функцій, React Query hook'ів і UI-компонентів. Це дозволяє не змішувати, наприклад, логіку заклять із логікою монстрів.

### `features/`

Тут живе логіка користувацьких сценаріїв.

У цьому каркасі вже є:

- `features/search` - пошук по каталогу
- `features/favorites` - Zustand store для улюбленого контенту

### `shared/`

Повторно використовуваний код без прив’язки до конкретної сутності.

- `shared/api/` - Axios instance
- `shared/constants/` - маршрути та інші константи
- `shared/hooks/` - універсальні hooks
- `shared/ui/` - базові компоненти інтерфейсу
- `shared/types/` - спільні типи

## Потік даних

Приклад для списку заклять:

```txt
SpellsPage
  -> useSpells()
  -> getSpells()
  -> Query Client
  -> mock data / backend API
  -> SpellCard
```

Це означає, що:

- сторінка не викликає HTTP напряму;
- вся робота з отриманням даних проходить через entity hook;
- пізніше можна замінити mock data на реальний бекенд без переписування сторінки.

## Маршрути

- `/` - головна
- `/classes` - класи
- `/classes/:id` - детальна сторінка класу
- `/races` - раси
- `/races/:id` - детальна сторінка раси
- `/spells` - закляття
- `/spells/:id` - детальна сторінка закляття
- `/monsters` - монстри
- `/monsters/:id` - детальна сторінка монстра
- `/search` - пошук

## Як додавати нову сутність

1. Створіть папку в `src/entities/<name>/`.
2. Додайте типи в `types/<name>.ts`.
3. Додайте API-функцію в `api/get<Name>.ts`.
4. Додайте React Query hook в `hooks/use<Name>.ts`.
5. Додайте картку або інший базовий UI в `components/<Name>Card.tsx`.
6. Використайте сутність на потрібній сторінці.

## Як додавати нову сторінку

1. Створіть папку в `src/pages/<PageName>/`.
2. Зберіть сторінку з готових entity та feature-компонентів.
3. Додайте маршрут в `src/app/router/router.tsx`.
4. За потреби додайте пункт в навігацію через `src/shared/constants/routes.ts`.

## Backend інтеграція

Поточний каркас уже має місця для підключення API:

- `shared/api/api.ts` - глобальний Axios instance;
- `entities/*/api/get*.ts` - конкретні запити по сутностях;
- `entities/*/hooks/use*.ts` - інтеграція з TanStack Query.

Зараз дані повертаються з локальних mock-структур, щоб UI та архітектура працювали без бекенду. Далі достатньо замінити mock data на `api.get(...)`.

## Запуск

```bash
npm install
npm run dev
```

Додатково:

```bash
npm run build
npm run lint
```
