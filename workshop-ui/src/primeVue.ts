import 'primeicons/primeicons.css';
import Column from 'primevue/column';
import PrimeVue from 'primevue/config';
import 'primevue/resources/primevue.min.css';

import 'primevue/resources/themes/saga-blue/theme.css';
import DataTable from 'primevue/datatable';
import TabMenu from 'primevue/tabmenu';
import type { App } from 'vue';

export default function registerPrimeVueComponents(app: App<Element>) {
    app.use(PrimeVue);

    app.component('TabMenu', TabMenu);
    app.component('DataTable', DataTable);
    app.component('Column', Column);
}
