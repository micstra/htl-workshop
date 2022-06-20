import RouteDefinition from '@/router/domain/RouteDefinition';
import HomeView from '@/views/HomeView.vue';

export const home = new RouteDefinition(
    '/',
    'Home',
    HomeView,
);
