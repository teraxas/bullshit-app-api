import { BullshitAppFrontendPage } from './app.po';

describe('bullshit-app-frontend App', function() {
  let page: BullshitAppFrontendPage;

  beforeEach(() => {
    page = new BullshitAppFrontendPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
