<script lang="ts">
import TabMenu from 'primevue/tabmenu';
import { defineComponent, ref } from 'vue';
import { useI18n } from 'vue-i18n';

interface MenuEntry {
  label: string;
  icon: string;
  to: string;
}

export default defineComponent({
  components: {
    TabMenu,
  },
  setup() {
    const { t } = useI18n({ useScope: 'global' });
    const menuEntries = ref<MenuEntry[]>([
      { label: t('header.overview'), icon: 'pi pi-fw pi-bell', to: '/' },
      { label: t('header.employees'), icon: 'pi pi-fw pi-user', to: '/employee/list' },
    ]);

    return {
      menuEntries,
    };
  },
});
</script>

<template>
  <header>
    <img alt="Cloudflight logo" class="logo" src="@/assets/logo.png" />
    <TabMenu :model="menuEntries" />
  </header>

  <RouterView />
</template>

<style>
@import 'assets/base.css';

header {
  position: fixed;
  height: 100px;
  width: 100%;
  top: 0;
  left: 0;
  right: 0;
  background-color: #1245f0;
}

header img {
  height: 100%;
  object-fit: contain;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  body {
    display: flex;
    place-items: center;
  }

  #app {
    display: grid;
    grid-template-columns: 1fr 1fr;
    padding: 0 2rem;
  }

  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  nav {
    text-align: left;
    margin-left: -1rem;
    font-size: 1rem;

    padding: 1rem 0;
    margin-top: 1rem;
  }
}
</style>
