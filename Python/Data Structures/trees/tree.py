from node import Node
class Tree(object):
    """
    Tree superclass
    """
    
    def __init__(self, value):
        """
        Constructs a new 'Tree' object

        :param value: Root value of th BST
        """
        self.root = Node(value)

    def insert(self, value):
        """
        Insert method for a Tree
        :param value: Value to insert
        """
        pass
    
    def inorder_traversal(self, node=None):
        """
        Method to perform inorder traversal on the tree
        :param node: Node to start the traversal
        """
        pass

    def preorder_traversal(self, node=None):
        """
        Method to perform preorder traversal on the tree
        :param node: Node to start the traversal
        """
        pass
    
    def postorder_traversal(self, node=None):
        """
        Method to perform postorder traversal on the tree
        :param node: Node to start the traversal
        """
        pass
    
    def find(self, value):
        """
        Find the value in the Tree
        :param node: Node to start the traversal
        :return: None if the value does not exist, a Node object if it does 
        """

    def delete(self, value):
        """
        Delete a node with the value from the Tree
        :param value: Value to delete
        :return: a new Tree object
        """
        pass
        