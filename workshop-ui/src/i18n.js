import { createI18n } from 'vue-i18n';
import de from './locales/de.json';
import en from './locales/en.json';

export const SUPPORT_LOCALES = ['de', 'en'];

export default createI18n({
    legacy: false,
    globalInjection: true,
    locale: 'de',
    fallbackLocale: 'en',
    messages: {
        de,
        en,
    },
});
