export const environment = {
  apiUrl: '/api',
  production: false,
  sessionTimeout: 30,
  auth: {
    loginUrl: '/logon',
    userStorage: sessionStorage,
    userStorageIntex: 'user'
  }
};
