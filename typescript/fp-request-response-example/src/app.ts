import express, {Request, Response} from 'express';

const app = express();
const port = 3000;

type HttpCall = (req: Request, res: Response) => void;

const logCall = (fn: HttpCall) => (req: Request, res: Response) => {
  console.log(`Call logged at ${req.path}`);
  fn(req, res)
}

const traceCall = (fn: HttpCall) => (req: Request, res: Response): void => {
  console.log(`Call scanned at ${req.path} on ${new Date()}`)
  fn(req, res)
}

const onBasePathRequest: HttpCall = (req, res) => {
  res.send('The sedulous hyena ate the antelope!');
}

const onHelloRequest: HttpCall = (req, res) => {
  res.send("Hello to you!");
}

const composed = (request: HttpCall) => logCall(traceCall(request));

app.get('/', composed(onBasePathRequest));

app.get('/hello', composed(onHelloRequest));

app.listen(port, err => {
  if (err) {
    return console.error(err);
  }
  return console.log(`server is listening on ${port}`);
});
