 proxy = [
  {
    context: '/api',
    target: 'http://localhost:8081',
    pathRewrite: {'^/api' : ''}
  }
];
module.exports = proxy;
