from easybert import Bert
import argparse
import sys

def create_arg_parser():
    """"Creates and returns the ArgumentParser object."""

    parser = argparse.ArgumentParser(description='Description of your app.')
    parser.add_argument('outputDirectory',
                    help='Path for saving model',
                    default='tf/model')
    return parser

if __name__ == "__main__":
    arg_parser = create_arg_parser()
    parsed_args = arg_parser.parse_args(sys.argv[1:])
    bert = Bert("https://tfhub.dev/google/bert_multi_cased_L-12_H-768_A-12/1")
    bert.save(parsed_args.outputDirectory)
