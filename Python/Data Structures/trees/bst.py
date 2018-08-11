from tree import Tree
from node import Node

class BST(Tree):
    """
    BST is a simple Binary Search Tree
    """

    def insert(self, value):
        """
        Performs BST insert algorithm
        :param value: Value to insert
        """
        if self.root == None:
            self.root = Node(value)
        else:
            self._insert(self.root, value)
    
    def _insert(self, node, value):
        """
        Private method to perform the BST insert recursively
        :param node: Current node
        :param value: value to insert
        """
        if value <= node.value:
            if node.left != None:
                self._insert(node.left, value)
            else:
                node.left = Node(value)
        
        else:
            if node.right != None:
                self._insert(node.right, value)
            else:
                node.right = Node(value)

    def inorder_traversal(self, node=None):
        """
        Performs inorder traversal
        :param node: Node to start the traversal on
        """
        if node == None:
            node = self.root
        self._inorder(node)
    
    def _inorder(self, node):
        """
        Private method to perform inorder traversal recursively
        :param node: Node to start the traversal on
        """
        if node != None:
            self._inorder(node.left)
            print(node.value)
            self._inorder(node.right)

    def preorder_traversal(self, node=None):
        """
        Performs preorder traversal
        :param node: Node to start the traversal on
        """
        if node == None:
            node = self.root
        self._preorder(node)
    
    def _preorder(self, node):
        """
        Private method to perform preorder traversal recursively
        :param node: Node to start the traversal on
        """
        if node != None:
            print(node.value)
            self._preorder(node.left)
            self._preorder(node.right)
    
    def postorder_traversal(self, node=None):
        """
        Performs postorder traversal
        :param node: Node to start the traversal on
        """
        if node == None:
            node = self.root
        self._postorder(node)
    
    def _postorder(self, node):
        """
        Private method to perform postorder traversal recursively
        :param node: Node to start the traversal on
        """
        if node != None:
            self._postorder(node.left)
            self._postorder(node.right)
            print(node.value)

    def find(self, val):
        """
        Finds a given value in a value in the BST
        :param val: value to find
        :return: None if it does not exist, the Node if it does
        """
        return self._find(self.root, val)

    def _find(self, current, val):
        """
        Private method to perform the find algorithm recursively
        :param current: Current node in the tree
        :param val: Value to find
        :return: None if it does not exist, the Node if it does
        """
        if current is None:
            return None
        elif val == current.value:
            return current
        elif val < current.value:
            return self._find(current.left, val)
        else:
            return self._find(current.right, val)
        
    def delete(self, value):
        """
        Performs the BST deletion algorithm
        :param value: Value to delete
        :return: The new BST
        """
        self.root = self._delete(self.root, value)
        return self
    
    def _delete(self, current, val):
        """
        Private method to perform the delete algorithm recursively
        :param current: Current node in the BST
        :param val: Value to delete
        :return: The newly updated Node
        """
        if current is None:
            return None
        elif val < current.value:
            current.left = self._delete(current.left, val)
        elif val > current.value:
            current.right = self._delete(current.right, val)
        else:
            if current.left == None:
                temp = current.right
                current = None
                return temp
            elif current.right == None:
                temp = current.left
                current = None
                return temp
            temp_min = self.min_value(current.right)
            print(temp_min)
            current.value = temp_min
            current.right = self._delete(current.right, temp_min)
        return current
    
    def min_value(self, current):
        """
        Finds the inorder successor for a Node with 2 children (the smallest value in the right subtree)
        :param current: Current node in the BST
        :return: Smallest value in the right subtree
        """
        if (current.left != None):
            return self.min_value(current.left)  
        return current.value
        