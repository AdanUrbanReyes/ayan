export class Menu {

    title: string;
    image: string;
    route: string;
    menus: Menu[];

    constructor(title : string, image : string, route : string, menus ?: Menu[]) {
        this.title = title;
        this.image = image;
        this.route = route;
        this.menus = menus || [];
    }

}