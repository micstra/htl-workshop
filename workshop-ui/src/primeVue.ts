import 'primeicons/primeicons.css';
import PrimeVue from 'primevue/config';
import 'primevue/resources/primevue.min.css';

import 'primevue/resources/themes/saga-blue/theme.css';
import TabMenu from 'primevue/tabmenu';
import type { App } from 'vue';

export default function registerPrimeVueComponents(app: App<Element>) {
    app.use(PrimeVue);

    app.component('TabMenu', TabMenu);
}
