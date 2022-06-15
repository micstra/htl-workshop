import registerPrimeVueComponents from '@/primeVue';
import { createApp } from 'vue';
import App from './App.vue';
import i18n from './i18n.js';
import router from './router';

const app = createApp(App);

app.use(router);
app.use(i18n);
registerPrimeVueComponents(app);

app.mount('#app');
