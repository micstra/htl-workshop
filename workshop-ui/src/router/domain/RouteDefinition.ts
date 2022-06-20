import type { RouteLocationRaw } from 'vue-router';

export default class RouteDefinition {
    public readonly path: string;
    public readonly name: string;
    public readonly component: any;

    constructor(path: string, name: string, component: any) {
        this.path = path;
        this.name = name;
        this.component = component;
    }


    public toRoute(): RouteLocationRaw {
        return { name: this.name };
    }

    public toRouteWithParams(params: any): RouteLocationRaw {
        return { name: this.name, params: params };
    }

}
