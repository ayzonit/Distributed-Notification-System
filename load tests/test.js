import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
  vus: 50,           // number of virtual users
  duration: '30s',   // how long test runs
};

export default function () {
  const payload = JSON.stringify({
    userId: 1,
    message: "Load test notification",
    priority: "STANDARD"
  });

  const params = {
    headers: { 'Content-Type': 'application/json' },
  };

  http.post('http://localhost:8092/api/notifications', payload, params);

  sleep(1);
}